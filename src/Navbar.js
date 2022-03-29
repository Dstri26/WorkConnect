//import { useState } from "react";
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const Navbar = () => {

    // const [isLogout,setIsLogout] =  useState(true);
    const navigate = useNavigate();
    const handleUserLogout= (e) => {
        e.preventDefault()
        let email= sessionStorage.getItem("wcEmail");
        const payload = {email};
        fetch('//13.127.82.222:8778/logoutUser/',{
            method : 'POST',
            headers : {"Content-Type":"application/json"},
            body: JSON.stringify(payload)
        }).then((res)=>{    
            return res.json();
        }).then((data)=>{
            if(data.status === '1'){
                sessionStorage.clear();
                navigate('/login');
            }
            
        })
    }
    const handleAdminLogout= (e) => {
        e.preventDefault()
        let email= sessionStorage.getItem("adminEmail");
        const payload = {email};
        fetch('//13.127.82.222:8778/logoutAdmin/',{
            method : 'POST',
            headers : {"Content-Type":"application/json"},
            body: JSON.stringify(payload)
        }).then((res)=>{    
            return res.json();
        }).then((data)=>{
            if(data.status === '1'){
                sessionStorage.clear();
                navigate('/login');
            }
            
        })
    }
    return ( 
        <div className="navClass">
            <nav className="navbar navbar-expand-lg">
                <Link to="\" className="navbar-brand">WorkConnect</Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item ">
                            {sessionStorage.getItem("wcEmail") && <Link to="/" className="nav-link"><i className="fa fa-user"></i>&nbsp;&nbsp;{sessionStorage.getItem("wcEmail")}</Link>}
                        </li>
                        <li className="nav-item">
                            {sessionStorage.getItem("wcEmail") && <Link to="/" onClick={handleUserLogout} className="nav-link"><i className="fas fa-sign-out-alt"></i>&nbsp;&nbsp;Signout</Link> }
                        </li>
                        <li className="nav-item ">
                            {sessionStorage.getItem("adminEmail") && <Link to="/tasks" className="nav-link"><i className="fa fa-list"></i>&nbsp;&nbsp;All Tasks</Link>}
                        </li>
                        <li className="nav-item ">
                            {sessionStorage.getItem("adminEmail") && <Link to="/users" className="nav-link"><i className="fa fa-users"></i>&nbsp;&nbsp;All Users</Link>}
                        </li>
                        <li className="nav-item ">
                            {sessionStorage.getItem("adminEmail") && <Link to="/admin" className="nav-link"><i className="fa fa-user"></i>&nbsp;&nbsp;{sessionStorage.getItem("adminEmail")}</Link>}
                        </li>
                        <li className="nav-item">
                            {sessionStorage.getItem("adminEmail") && <Link to="/" onClick={handleAdminLogout} className="nav-link"><i className="fas fa-sign-out-alt"></i>&nbsp;&nbsp;Signout</Link> }
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
     );
}
 
export default Navbar;