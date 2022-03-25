import './App.css';
import Navbar from './Navbar';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TaskPage from './TaskPage';
import Home from './Home';
import Login from './Login';


function App() {
  // const email = "trideep.india@gmail.com";
  // const { data:tasks, isPending, error} = useAxios('http://localhost:9191/TaskByEmail/'+ email);
  // console.log(tasks);
  return (
    <Router>
    <div className="App">
      <Navbar></Navbar>
        <div className="content">
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/asana" element={<TaskPage platform="asana"/>} />
            <Route path="/slack" element={<TaskPage platform="slack" />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
