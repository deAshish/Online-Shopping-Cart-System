import { useState } from "react";
import { useEffect } from "react";

function ExamPrep() {
  let [counter, setCounter] = useState({
    value: 1,
  });

  useEffect(() => {
    // Update the document title using the browser API
    document.title = `You clicked ${counter} times`;
  });

  return (
    <div>
      <p>You clicked {counter.value} times</p>
      <button
        type="submit"
        onClick={(e) => setCounter({ value: counter.value + 1 })}
      >
        CLICK
      </button>
    </div>
  );
}

export default ExamPrep;
