import { useState } from 'react'
import './App.css'
import { ToDoForm } from './components/ToDoForm';

function App() {

  const [todos, setTodos] = useState([]);

  function addTodo(title){
    setTodos((currentTodo) => {
      return [
        ...currentTodo,
        {
          id: crypto.randomUUID(),
          title: title,
          completed: false
        }
      ]
    });
  }
  
  function toggleToDo(id, completed){
    setTodos((currentTodos) => {
      return currentTodos.map((todo) => {
        if(todo.id === id){
          return {...todo, completed}
        }
        return todo;
      })

    })
  }

  function deleteTodo(id){
    setTodos((currentTodos) => {
      return currentTodos.filter((todo) => todo.id !== id);
    })
  }

  return (
    <>
    <h1 className="header">ToDo Application</h1>
    <ToDoForm/>

      <br />
      <h1>Tasks</h1>

        <ul className="list">
          {todos.length === 0 && "No Todos"}
          {todos.map(todo => {
            return (
              <li key={todo.id}>
                <label>
                  <input type="checkbox" 
                    checked={todo.completed}
                    onChange={event => toggleToDo(todo.id, event.target.checked)}
                  />
                  {todo.title}
                </label>
                <button className="btn btn-danger" onClick={(event) => {deleteTodo(todo.id)}}>Delete</button>
              </li>
            )
          })}
        </ul>
    </>
  )
}

export default App
