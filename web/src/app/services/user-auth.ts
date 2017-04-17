import {UserRequest} from "../domain/request/user-request";

export interface IUserAuth<T> {

  register(userRequest: UserRequest):Promise<T>

  signIn(userRequest: UserRequest):Promise<T>

  logOut():Promise<any>
}
