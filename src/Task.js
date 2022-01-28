const Task = ({task}) => {
    return ( 
        <div className="task">
            <div className={"alert text-left " + ( task.status === 0 ? 'alert-warning ' : 'alert-secondary text-muted')} role="alert">
                    <div className="row">
                        <div className="d-flex col-md-11 align-items-center">
                            <div>
                                <small>{task.time} :</small>
                                <p><em>{task.taskName}</em></p>
                            </div>
                            
                        </div>
                        <div className="col-md-1 text-center d-flex align-items-center justify-content-center symbols">
                            <div className="row">
                                <div className="col-12">
                                    <i className={"fas " + ( task.status === 0 ? 'fa-check ' : 'fa-times text-muted')}></i>
                                </div>
                            </div>
                        </div>
                    </div>
               
                </div>
        </div>
     );
}
 
export default Task;