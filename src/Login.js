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


    const [lemail,setLEmail]= useState('');
    const [lpassword,setLPassword]= useState('');
    const [lmsg,setLMsg]= useState('');

    const handleLogin= (e) => {
        e.preventDefault();
        var email = lemail;
        var password = lpassword;
        const payload = {email, password};
        fetch('//13.127.82.222:9191/loginUser/',{
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
                        <form>
                            <div className="form-group">
                                <input type="text" required="" autofocus="" className="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Your Email" value="" />
                            </div>
                            <div className="form-group">
                                <input type="password" required="" autofocus="" className="form-control rounded-pill border-0 shadow-sm px-4"  placeholder="Your Password " value="" />
                            </div>
                            <br />
                            <div className="form-group">
                                <input type="submit" className="btnSubmit" value="Login" />
                            </div>
                            <div className="form-group">
                                <Link to="/register" className="RegisterBtn">Not Registered?</Link>
                            </div>
                        </form>
                    </div>
                    <div className="col-md-6 login-form-2">
                        <h3><i className="fas fa-lock-alt"></i> &nbsp; Admin Login </h3>
                        <form>
                            <div className="form-group">
                                <input type="text" placeholder="Your Email" value="" required="" autofocus="" className="form-control rounded-pill border-0 shadow-sm px-4"  />
                            </div>
                            <div className="form-group">
                                <input type="password" placeholder="Your Password" value="" required="" autofocus="" className="form-control rounded-pill border-0 shadow-sm px-4" />
                            </div>
                            <br />
                            <div className="form-group">
                                <input type="submit" className="btnSubmit" value="Login" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            {/* <div className="row">
                <div className="col-md-6 d-flex align-items-center justify-content-center">
                    <div className="card">
                        <div className="card-body">
                            <form onSubmit={handleLogin}>
                                <p className="lead">Login Yourself</p>
                                <hr />
                                <div className="form-group">
                                    <input 
                                        type="email" 
                                        className="form-control" 
                                        name="lemail" 
                                        id="lemail" 
                                        value={lemail}
                                        onChange={(e)=>setLEmail(e.target.value)}
                                        required
                                        placeholder="Enter your email"
                                    />
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password" 
                                        className="form-control" 
                                        name="lpassword" 
                                        id="lpassword"
                                        value={lpassword}
                                        onChange={(e)=>setLPassword(e.target.value)}
                                        required
                                        placeholder="Enter your password"
                                    />
                                </div>
                                <div className="form-group">
                                    {lmsg && <p className="text-success">{lmsg}</p>}
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                                
                            </form>
                        </div>
                    </div>
                </div>

            </div> */}
        </div>
     );
}
 
export default Login;