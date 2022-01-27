import { Link } from 'react-router-dom';


const SideBar = () => {
    return ( 
        <div className="sidebar">
            <header className="header" id="header">
                <div className="header_toggle"> <i className='fas fa-bars' id="header-toggle"></i> </div>
            </header>
            <div className="l-navbar" id="nav-bar">
                <nav className="nav">
                    <div> 
                        <Link to="/"className="nav_logo"> <i className="fas fa-building"></i> <span className="nav_logo-name">Work Connect</span> </Link>
                        <div className="nav_list">
                            <Link to="/"className="nav_link active"> <i className="fas fa-user"></i> <span className="nav_name">Profile</span> </Link>
                            <Link to="/asana"className="nav_link"> <i className="fas fa-network-wired"></i> <span className="nav_name">Asana</span> </Link>
                            <Link to="/slack"className="nav_link"> <i className="fab fa-slack"></i> <span className="nav_name">Slack</span> </Link>
                            <Link to="/workday"className="nav_link"> <i className="fab fa-wikipedia-w"></i> <span className="nav_name">Workday</span> </Link>
                            <Link to="/"className="nav_link"> <i className="fas fa-cogs"></i> <span className="nav_name">Settings</span> </Link> 
                            {/* <Link to="/"className="nav_link"> <i className="fas fa-chart-line"></i> <span className="nav_name">Stats</span> </Link>  */}
                        </div>
                    </div> 
                    <Link to="/"className="nav_link"><i className="fas fa-sign-out-alt"></i> <span className="nav_name">SignOut</span> </Link>
                </nav>
            </div>
        </div>
     );
}
 
export default SideBar;