import { OrderItems } from "./order-items";
import { ShippingAddress } from "./shipping-address";
import { User } from "./user";

export class OrderRequest {

  constructor(

    public user:User,
    public shippingAddress:ShippingAddress,
    public orderItems:OrderItems[],
    public totalQuantity:number,
    public totalPrice:number
  ){
    
  }
}
