export abstract class RequestMapping{
  private static commonUrl = "api/v1/";

  //auth
  static register = RequestMapping.commonUrl + "auth/register?role={0}";
  static login = RequestMapping.commonUrl + "auth/login?role={0}";
  static logout = RequestMapping.commonUrl + "auth/logout";

  //user info
  static getCurrentUser = RequestMapping.commonUrl + "user/info";
  static getCurrentRole = RequestMapping.commonUrl + "user/role";

  //seller
  static getAllSellers = RequestMapping.commonUrl + "seller/all";
  static getSellerById = RequestMapping.commonUrl + "seller/{0}";
  static updateAddInfoSeller = RequestMapping.commonUrl + "seller/update/add-info";
  static getSellerPurchases = RequestMapping.commonUrl + "seller/purchases";

  //buyer

  //purchase
  static createPurchase = RequestMapping.commonUrl + "purchase/new";
}
