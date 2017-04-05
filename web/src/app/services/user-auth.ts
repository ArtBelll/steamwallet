import {UserRequest} from "../domain/request/userRequest";

export interface IUserAuth<T> {

  register(userRequest: UserRequest):Promise<T>

  signIn(userRequest: UserRequest):Promise<T>

  logOut():Promise<any>
}
