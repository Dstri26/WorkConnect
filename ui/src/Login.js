import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

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

    const [rname,setRName] = useState('');
    const [remail,setREmail]= useState('');
    const [rpassword,setRPassword]= useState('');
    const [rphone,setRPhone]= useState('');
    const [rmsg,setRMsg]= useState('');

    const handleLogin= (e) => {
        e.preventDefault();
        var email = lemail;
        var password = lpassword;
        const payload = {email, password};
        fetch('http://localhost:9191/loginUser/',{
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

    const handleRegister= (e) => {
        e.preventDefault();
        var name = rname;
        var email = remail;
        var password = rpassword;
        var phoneNo = rphone;
        const payload = {name, email, password, phoneNo};
        fetch('http://localhost:9191/registerUser/',{
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
                setRMsg(data.msg);
            }
        })
    }


    return ( 
        <div className="login-page">
            <div className="row">
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

                <div className="col-md-6 d-flex align-items-center justify-content-center">
                    <div className="card">
                        <div className="card-body">
                            <form onSubmit={handleRegister}>
                                    <p className="lead">Register Yourself</p>
                                    <hr />
                                    <div className="form-group">
                                        <input 
                                            type="text" 
                                            className="form-control" 
                                            name="rname" 
                                            id="rname" 
                                            value={rname}
                                            onChange={(e)=>setRName(e.target.value)}
                                            required
                                            placeholder="Enter your full name"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input 
                                            type="email" 
                                            className="form-control" 
                                            name="remail" 
                                            id="remail" 
                                            value={remail}
                                            onChange={(e)=>setREmail(e.target.value)}
                                            required
                                            placeholder="Enter your email"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input 
                                            type="password" 
                                            className="form-control" 
                                            name="rpassword" 
                                            id="rpassword"
                                            value={rpassword}
                                            onChange={(e)=>setRPassword(e.target.value)}
                                            required
                                            placeholder="Enter your password"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input 
                                            type="text" 
                                            className="form-control" 
                                            name="rphone" 
                                            id="rphone" 
                                            value={rphone}
                                            onChange={(e)=>setRPhone(e.target.value)}
                                            required
                                            placeholder="Enter your phone number"
                                        />
                                    </div>
                                    <div className="form-group">
                                        {rmsg && <p className="text-success">{rmsg}</p>}
                                    </div>
                                    <button type="submit" className="btn btn-primary">Register</button>
                                    
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     );
}
 
export default Login;