//import { useState } from "react";
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const Navbar = () => {

    // const [isLogout,setIsLogout] =  useState(true);
    const navigate = useNavigate();
    const handleLogout= (e) => {
        e.preventDefault()
        let email= sessionStorage.getItem("wcEmail");
        const payload = {email};
        fetch('http://localhost:9191/logoutUser/',{
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
                <Link to="/" className="navbar-brand" >WorkConnect</Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item ">
                        {sessionStorage.getItem("wcEmail") && <Link to="/" className="nav-link"><i className="fa fa-user"></i>&nbsp;&nbsp;{sessionStorage.getItem("wcEmail")}</Link>}
                        </li>
                        <li className="nav-item">
                            {sessionStorage.getItem("wcEmail") && <Link to="/" onClick={handleLogout} className="nav-link"><i className="fas fa-sign-out-alt"></i>&nbsp;&nbsp;Signout</Link> }
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
     );
}
 
export default Navbar;