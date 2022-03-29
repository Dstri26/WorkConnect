import { useState,useEffect } from 'react';
import { Link,useNavigate } from 'react-router-dom';



const Home = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("wcEmail") === null) {
            navigate('/register');
        }
    })
    
    //console.log(sessionStorage.getItem("wcEmail"));
    const [slackUpdate, setSlackUpdate] = useState(true);
    const [asanaUpdate, setAsanaUpdate] = useState(true);
    useEffect(() => {
        fetch('//13.127.82.222:8778/fetchSlack/')
        .then((res)=>{
            setSlackUpdate(null)
        })
        fetch('//13.127.82.222:8778/fetchAsana/')
        .then((res)=>{
            setAsanaUpdate(null)
        })
      }, []);
    return ( 
        <div className="home d-flex align-items-center justify-content-center">
            <div className="row">
                <div className="col-md-4">
                    <div className="card home-card">
                        <div className="card-body text-center">
                            <p className="plat-txt">Asana</p>
                            <p className="task-txt">Tasks</p>
                            <Link to="/asana"  className="btn btn-md btn-light">Explore</Link>
                        </div>
                        {asanaUpdate && <div className="card-footer">
                             <small>Updating...</small>   
                        </div>} 
                    </div>
                </div>
                <div className="col-md-4"></div>
                <div className="col-md-4">
                    <div className="card home-card">
                        <div className="card-body text-center">
                            <p className="plat-txt">Slack</p>
                            <p className="task-txt">Tasks</p>
                            <Link to="/slack"  className="btn btn-md btn-light">Explore</Link>
                        </div>
                        {slackUpdate && <div className="card-footer">
                             <small>Updating...</small>   
                        </div>} 
                    </div>
                </div>
            </div>
        </div>
     );
}
 
export default Home;