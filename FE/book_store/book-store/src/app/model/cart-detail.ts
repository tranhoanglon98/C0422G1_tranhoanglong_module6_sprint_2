import {Book} from "./book";

export interface CartDetail {
  id?: number,
  quantity?: number,
  status?: boolean,
  account?: any,
  book?: Book
}
