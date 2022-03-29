import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';

const Register = () => {
    const navigate = useNavigate();
    //console.log(sessionStorage.getItem("wcEmail"));
    useEffect(() => {
        if (sessionStorage.getItem("wcEmail") != null) {
            navigate('/');
        }
    })

    const [rname,setRName] = useState('');
    const [remail,setREmail]= useState('');
    const [rpassword,setRPassword]= useState('');
    const [rphone,setRPhone]= useState('');
    const [rmsg,setRMsg]= useState('');

    const handleRegister= (e) => {
        e.preventDefault();
        var name = rname;
        var email = remail;
        var password = rpassword;
        var phoneNo = rphone;
        const payload = {name, email, password, phoneNo};
        fetch('//13.127.82.222:8778/registerUser/',{
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
        <div className="register-page">
            <div className="container-fluid">
    <div className="row no-gutter">

        <div className="col-md-6 d-none d-md-flex bg-image"></div>

        <div className="col-md-6">
            <div className="login d-flex align-items-center py-5">

                <div className="container">
                    <div className="row">
                                    <div className="col-lg-10 col-xl-7 mx-auto">
                                        <p className="h1">Register Yourself</p>
                                        <p className="h6"><Link to="/login" className="RegisterBtn">Already Registered?</Link></p>
                                        <br />
                                        <form onSubmit={handleRegister} >
                                        <div className="form-group mb-3">
                                                <input 
                                                    name="rname" 
                                                    id="rname"
                                                    type="text"
                                                    value={rname}
                                                    onChange={(e)=>setRName(e.target.value)}
                                                    required
                                                    placeholder="Enter your full name"
                                                    autofocus="" 
                                                    className="form-control rounded-pill border-0 shadow-sm px-4" 
                                                />
                                            </div>
                                            
                                            <div className="form-group mb-3">
                                                <input 
                                                    name="remail" 
                                                    id="remail" 
                                                    type="email"
                                                    value={remail}
                                                    onChange={(e)=>setREmail(e.target.value)}
                                                    required
                                                    placeholder="Enter your email"
                                                    autofocus="" 
                                                    className="form-control rounded-pill border-0 shadow-sm px-4" 
                                                />
                                            </div>
                                            <div className="form-group mb-3">
                                                <input 
                                                    name="rpassword" 
                                                    id="rpassword"
                                                    type="password"
                                                    value={rpassword}
                                                    onChange={(e)=>setRPassword(e.target.value)}
                                                    placeholder="Enter Password"
                                                    required
                                                    className="form-control rounded-pill border-0 shadow-sm px-4 text-primary" 
                                                />
                                            </div>
                                            <div className="form-group mb-3">
                                                <input 
                                                    name="rphone" 
                                                    id="rphone"
                                                    type="text"
                                                    value={rphone}
                                                    onChange={(e)=>setRPhone(e.target.value)}
                                                    required
                                                    placeholder="Enter Phone Number"
                                                    autofocus="" 
                                                    className="form-control rounded-pill border-0 shadow-sm px-4" 
                                                />
                                            </div>
                                            <div className="form-group mb-3">
                                                {rmsg && <p className="text-success">{rmsg}</p>}
                                            </div>
                                            <br />
                                            <button type="submit" className="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm">Register</button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
     );
}
 
export default Register;