import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';

const Login = () => {
    const navigate = useNavigate();
    //console.log(sessionStorage.getItem("wcEmail"));
    useEffect(() => {
        if (sessionStorage.getItem("wcEmail") != null) {
            navigate('/');
        }
    })
    const [aemail,setAEmail]= useState('');
    const [apassword,setAPassword]= useState('');
    const [amsg,setAMsg]= useState('');

    const handleAdminLogin= (e) => {
        e.preventDefault();
        var email = aemail;
        var password = apassword;
        const payload = {email, password};
        fetch('//13.127.82.222:8778/loginAdmin/',{
            method : 'POST',
            headers : {"Content-Type":"application/json"},
            body: JSON.stringify(payload)
        }).then((res)=>{    
            return res.json();
        }).then((data)=>{
            console.log(data);
            if (data.status === "1") {
                sessionStorage.setItem("adminEmail", data.email);
                navigate('/admin');
            }
            else{
                setAMsg(data.msg);
            }
            
            
        })
    }


    const [lemail,setLEmail]= useState('');
    const [lpassword,setLPassword]= useState('');
    const [lmsg,setLMsg]= useState('');

    const handleLogin= (e) => {
        e.preventDefault();
        var email = lemail;
        var password = lpassword;
        const payload = {email, password};
        fetch('//13.127.82.222:8778/loginUser/',{
            method : 'POST',
            headers : {"Content-Type":"application/json"},
            body: JSON.stringify(payload)
        }).then((res)=>{    
            return res.json();
        }).then((data)=>{
            if (data.status === "1") {
                sessionStorage.setItem("wcEmail", data.email);
                navigate('/');
            }
            else{
                setLMsg(data.msg);
            }
            
            
        })
    }


    return ( 
        <div className="login-page">
            <div className="container login-container">
                <div className="row">
                    <div className="col-md-6 login-form-1">
                        <h3><i className="fas fa-user"></i>&nbsp;User Login</h3>
                        <form onSubmit={handleLogin}>
                            <div className="form-group">
                                <input 
                                    type="email" 
                                    name="lemail" 
                                    id="lemail" 
                                    value={lemail}
                                    onChange={(e)=>setLEmail(e.target.value)}
                                    required
                                    
                                    className="form-control rounded-pill border-0 shadow-sm px-4" 
                                    placeholder="Enter your Email" 
                                />
                            </div>
                            <div className="form-group">
                                <input 
                                    type="password" 
                                    name="lpassword" 
                                    id="lpassword"
                                    value={lpassword}
                                    onChange={(e)=>setLPassword(e.target.value)}
                                    required 
                                    
                                    className="form-control rounded-pill border-0 shadow-sm px-4"  
                                    placeholder="Your Password " 
                                />
                            </div>
                            <div className="form-group">
                                    {lmsg && <p className="text-success">{lmsg}</p>}
                                </div>
                            <br />
                            <div className="form-group">
                                <input type="submit" className="btnSubmit" value="User Login" />
                            </div>
                            <div className="form-group">
                                <Link to="/register" className="RegisterBtn">Not Registered?</Link>
                            </div>
                        </form>
                    </div>
                    <div className="col-md-6 login-form-2">
                        <h3><i className="fas fa-lock-alt"></i> &nbsp; Admin Login </h3>
                        <form onSubmit={handleAdminLogin}>
                        <div className="form-group">
                                <input 
                                    type="email" 
                                    name="aemail" 
                                    id="aemail" 
                                    value={aemail}
                                    onChange={(e)=>setAEmail(e.target.value)}
                                    required
                                    className="form-control rounded-pill border-0 shadow-sm px-4" 
                                    placeholder="Enter your Email" 
                                />
                            </div>
                            <div className="form-group">
                                <input 
                                    type="password" 
                                    name="apassword" 
                                    id="apassword"
                                    value={apassword}
                                    onChange={(e)=>setAPassword(e.target.value)}
                                    required 
                                    
                                    className="form-control rounded-pill border-0 shadow-sm px-4"  
                                    placeholder="Your Password " 
                                />
                            </div>
                            <div className="form-group">
                                    {amsg && <p className="text-light">{amsg}</p>}
                            </div>
                            <br />
                            <div className="form-group">
                                <input type="submit" className="btnSubmit" value="Admin Login" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
     );
}
 
export default Login;