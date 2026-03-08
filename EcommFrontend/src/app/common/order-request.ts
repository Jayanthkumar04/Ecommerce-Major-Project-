import { OrderItems } from "./order-items";
import { ShippingAddress } from "./shipping-address";

export class OrderRequest {

  constructor(

    public shippingAddress:ShippingAddress,
    public orderItems:OrderItems[],
    public totalQuantity:number,
    public totalPrice:number,
    public email:string
  ){
    
  }
}
