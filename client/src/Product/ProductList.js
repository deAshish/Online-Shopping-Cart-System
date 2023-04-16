import "./product.css";
import { useContext } from "react";
import { ResponseContext } from "./ProductsShow";

function ProductList(props) {
  const resContext = useContext(ResponseContext);

  return (
    <div className="general">
      {/* <span className='responseMessage'>{resContext}</span> */}
      <h4>Product name:</h4> {props.name}
      <h4>Product category:</h4> {props.productCategory}
      <h4>Product price:</h4> {props.price}$<br></br>
      <img className="productimage" src={props.imagePath} />
      <br />
      <input type="button" value="Update" className="spacebutton"></input>
      <input
        type="button"
        value="Delete"
        onClick={() => props.deleteProduct(props.id)}
      ></input>
    </div>
  );
}

export default ProductList;
