import './App.css';
import Navbar from './Navbar';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TaskPage from './TaskPage';
import Home from './Home';
import Login from './Login';
import Register from './Register';
import AdminUserPage from './AdminUserPage';
import AdminTaskPage from './AdminTaskPage';


function App() {
  return (
    <Router>
    <div className="App">
        <Navbar></Navbar>
        <div className="content">
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/admin" element={<AdminUserPage/>} />
            <Route path="/users" element={<AdminUserPage/>} />
            <Route path="/tasks" element={<AdminTaskPage/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/register" element={<Register/>} />
            <Route path="/asana" element={<TaskPage platform="asana"/>} />
            <Route path="/slack" element={<TaskPage platform="slack" />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
