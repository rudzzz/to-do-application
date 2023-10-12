import { useState } from 'react'

export function ToDoForm(){    
    const [newItem, setNewItem] = useState("");

    function handleSubmit(event){
        event.preventDefault();
        if(newItem === "") return;
        
        addTodo(newItem);
        setNewItem("");
    }

    return (
        <>
            <form onSubmit={handleSubmit} className="new-item-form">
                <div className="form-row">
                <label htmlFor="item">New Item</label>
                <input type="text" id="item" value={newItem} onChange={event => setNewItem(event.target.value)}/>
                </div>

                <button className="btn">Add</button>
            </form>
        </>
    )
}