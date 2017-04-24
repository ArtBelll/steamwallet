export class ErrorHandler {

  static handleError(error:any) {
    console.error('An error occurred', error);
    return Promise.reject(error.json()['error']['index']);
  }
}
