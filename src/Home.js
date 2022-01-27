import { Link } from 'react-router-dom';



const Home = ({tasks}) => {
    return ( 
        <div className="home d-flex align-items-center justify-content-center">
            <div className="row">
                <div className="col-md-4">
                    <div className="card home-card">
                        <img className="card-img-top" src="data:image/jpeg;base64,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" alt="Card image cap" />
                        <div className="card-body text-center">
                            <p className="plat-txt">Asana</p>
                            <p className="task-txt">Tasks</p>
                            <Link to="/asana"  className="btn btn-md btn-light">Explore</Link>
                        </div>
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="card  home-card">
                        <img className="card-img-top" src="https://a.slack-edge.com/80588/marketing/img/meta/slack_hash_256.png" alt="Card image cap" />
                        <div className="card-body text-center">
                            <p className="plat-txt">Slack</p>
                            <p className="task-txt">Tasks</p>
                            <Link to="/slack"  className="btn btn-md btn-light">Explore</Link>
                        </div>
                    </div>
                </div>
                <div className="col-md-4 ">
                    <div className="card home-card">
                        <img className="card-img-top" src="https://pbs.twimg.com/profile_images/1410603584824045570/OWC2lhJQ_400x400.jpg" alt="Card image cap" />
                        <div className="card-body text-center">
                            <p className="plat-txt">Workday</p>
                            <p className="task-txt">Tasks</p>
                            <Link to="/workday"  className="btn btn-md btn-light">Explore</Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     );
}
 
export default Home;