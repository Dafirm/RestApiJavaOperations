import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";

const firstbook ={
    title : "Good Energy",
    author:"Casey Means MD",
    img:
    "https://images-na.ssl-images-amazon.com/images/I/71KTwO53SnL._AC_UL600_SR600,400_.jpg"

}
const secondbook = {
  title: "Who Could Ever Love You",
  author: "Mary L. Trump PhD",
  img: "https://m.media-amazon.com/images/I/81ELJ0ei3OL._SY466_.jpg",
};
 

const BookList = () => {
  return (
    <section className="booklist">
      <Book author={firstbook.author} title={firstbook.title} img={firstbook.img} />
      <Book author={secondbook.author} title={secondbook.title} img={secondbook.img} />
    </section>
  );
};

const Book = (props) => {
  return (
    <article className="book">
      <h2>{props.title}</h2>
      <h4>{props.author}</h4>
      <img src={props.img} alt={props.title}/>
      
    </article>
  );
};

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(<BookList />);
