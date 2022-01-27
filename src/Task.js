const Task = ({task}) => {
    return ( 
        <div className="task">
            <div className="alert alert-warning text-left" role="alert">
                    <div className="row">
                        <div className="d-flex col-md-11 align-items-center">
                            <div>
                                <small>{task.time} :</small>
                                <p><em>{task.taskName}</em></p>
                            </div>
                            
                        </div>
                        <div className="col-md-1 text-center symbols">
                            <div className="row ">
                                <div className="col-12">
                                    <i className="fas fa-times"></i>
                                </div>
                            </div>
                            <hr />
                            <div className="row">
                                <div className="col-12">
                                    <i className="fas fa-check"></i>
                                </div>
                            </div>
                        </div>
                    </div>
               
                </div>
        </div>
     );
}
 
export default Task;