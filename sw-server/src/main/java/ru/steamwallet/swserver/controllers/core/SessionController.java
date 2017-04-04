package ru.steamwallet.swserver.controllers.core;

import com.sun.istack.internal.NotNull;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.steamwallet.swcommon.dao.BuyerDao;
import ru.steamwallet.swcommon.dao.SellerDao;
import ru.steamwallet.swcommon.domain.Buyer;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.exceptions.InvalidAccessToken;
import ru.steamwallet.swcommon.exceptions.ResourceNotFoundException;
import ru.steamwallet.swcommon.exceptions.UnAuthorized;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Sergey Ignatov on 11/04/16.
 */
@Slf4j
public abstract class SessionController {

    private final static String KEY = "gbo-4w6d-jp9-jbx8-74r-47c5-nwj-aobe-ba8-wfym-4os-kqmj-jzd-sh35-piu-ufaf-nq7-gikj-g38-357r-i6f-83qz-rfq-ot7p-zyr-dcjz-m8h-r6gr-u5u-9wds-bhz-3jcm-hq5-i8ir-t9d-nd8h-rgg-3is3o";
    private final static String K64 = TextCodec.BASE64.encode(KEY);

    private static final String TOKEN_HEADER = "X-SteamWallet-Token";
    private static final String USER_ID = "user_id";

    @Autowired
    protected SellerDao sellerDao;

    @Autowired
    protected BuyerDao buyerDao;

    protected String setSessionSeller(final @NonNull Seller seller) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, seller.getId());
        return Jwts.builder()
                .setSubject(seller.getLogin())
                .setIssuedAt(new Date())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, K64)
                .compact();
    }

    protected String setSessionBuyer(final @NonNull Buyer buyer) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, buyer.getId());
        final String payload = Jwts.builder()
                .setSubject(buyer.getLogin())
                .setIssuedAt(new Date())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, K64)
                .compact();
        return payload;
    }

    protected Seller getSessionSeller(final @NotNull HttpServletRequest request) {
        final Cookie[] cookies = Optional.ofNullable(request.getCookies())
                .orElseThrow(() -> new UnAuthorized("No cookies set"));
        final String payload = Stream.of(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(TOKEN_HEADER))
                .findFirst()
                .orElseThrow(() -> new UnAuthorized("There's no X-SteamWaller-Token cookie"))
                .getValue();
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(K64)
                    .parseClaimsJws(payload)
                    .getBody();
            final int id = Optional.of((int)claims.get(USER_ID)).orElseThrow(UnAuthorized::new);
            final Seller seller = sellerDao.get((long)id);
            if (seller == null) {
                throw new ResourceNotFoundException("user_id", id);
            }
            return seller;
        } catch (ResourceNotFoundException e) {
            log.error("getSessionUser ResourceNotFoundException: {}", e);
            throw new UnAuthorized();
        } catch (ExpiredJwtException |UnsupportedJwtException|MalformedJwtException |SignatureException|IllegalArgumentException e) {
            log.error("getSessionUser parseClaimsJws failed: {}", e);
            throw new UnAuthorized();
        }
    }

    protected Buyer getSessionBuyer(final @NotNull HttpServletRequest request) {
        final Cookie[] cookies = Optional.ofNullable(request.getCookies())
                .orElseThrow(() -> new UnAuthorized("No cookies set"));
        final String payload = Stream.of(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(TOKEN_HEADER))
                .findFirst()
                .orElseThrow(() -> new UnAuthorized("There's no X-SteamWaller-Token cookie"))
                .getValue();
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(K64)
                    .parseClaimsJws(payload)
                    .getBody();
            final Long id = Optional.of((Long)claims.get(USER_ID)).orElseThrow(UnAuthorized::new);
            final Buyer buyer = buyerDao.get(id);
            if (buyer == null) {
                throw new ResourceNotFoundException("user_id", id);
            }
            return buyer;
        } catch (ResourceNotFoundException e) {
            log.error("getSessionUser ResourceNotFoundException: {}", e);
            throw new UnAuthorized();
        } catch (ExpiredJwtException |UnsupportedJwtException|MalformedJwtException |SignatureException|IllegalArgumentException e) {
            log.error("getSessionUser parseClaimsJws failed: {}", e);
            throw new UnAuthorized();
        }
    }

    protected static String getRequestIp(HttpServletRequest request) {
        InetAddress inetAddress;
        try {
            final String xRealIp = request.getHeader("X-Real-IP");
            final String remoteAddr = xRealIp != null ? xRealIp : request.getRemoteAddr();
            inetAddress = InetAddress.getByName(remoteAddr);
        } catch (UnknownHostException ignore) {
            inetAddress = null;
        }

        String ip = "";
        if (inetAddress != null) {
            ip = inetAddress.toString();
            if (ip.startsWith("/"))
                ip = ip.substring(1);
        }

        return ip;
    }

    protected ResponseEntity<?> createdResponse(final Object data,
                                                final boolean created,
                                                final String jwt,
                                                final boolean ssl) {
        final HttpHeaders headers = new HttpHeaders();
        if (jwt != null && !"".equals(jwt)) {
            int expire = 60 * 60 * 24 * 365;
            setJwtToken(headers, jwt, ssl, expire);
        }
        return new ResponseEntity<>(data, headers, created ? HttpStatus.CREATED : HttpStatus.OK);
    }

    private void setJwtToken(final HttpHeaders headers, final String jwt, final boolean ssl, int expire) {
        headers.add(HttpHeaders.SET_COOKIE,
                TOKEN_HEADER + "=" + jwt +
                "; Max-Age=" + Integer.toString(expire) +
                (ssl ? "; Secure" : "; HttpOnly") +
                "; Path=/api/v1/");
    }

    protected ResponseEntity<?> closeSession() {
        final HttpHeaders headers = new HttpHeaders();
        setJwtToken(headers, "", false, 1);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    protected static boolean isSslRequest(final @NotNull HttpServletRequest request) {
        return request.getHeader("X-Real-IP") != null;
    }
}
