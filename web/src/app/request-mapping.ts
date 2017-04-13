export abstract class RequestMapping{
  private static commonUrl = "api/v1/";

  static register = RequestMapping.commonUrl + "auth/register?role={0}";
  static login = RequestMapping.commonUrl + "auth/login?role={0}";
  static logout = RequestMapping.commonUrl + "auth/logout";
  static checkSession = RequestMapping.commonUrl + "user/info";

}
