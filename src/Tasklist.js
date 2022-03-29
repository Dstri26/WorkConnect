const TaskList = ({tasks,handleUpdate}) => {

    return ( 
        <div className="task-list ">
            { tasks.map((task)=>(
                <div className="task" key={task.id}>
                <div className={"alert text-left " + ( task.status === 0 ? 'alert-primary ' : 'alert-secondary text-muted')} role="alert">
                        <div className="row">
                            <div className="d-flex col-md-11 align-items-center">
                                <div>
                                    <p className="lead"><span class="badge badge-pill badge-warning">{task.projectName}</span></p>
                                    <p className="taskTxt h6"> {task.taskName}</p>
                                    <hr />
                                    <span class="time"><small>{task.time.slice(0,10)} {task.time.slice(11,19)}</small> </span>
                                </div>
                                
                            </div>
                            <div className="col-md-1 text-center d-flex align-items-center justify-content-center symbols">
                                <div className="row">
                                    <div className="col-12">
                                        <i onClick={() => handleUpdate(task.id)} className={"fas " + ( task.status === 0 ? 'fa-check text-success' : 'fa-trash text-danger+ ')}></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                   
                    </div>
                </div>
            ))}
        </div>
     );
}
 
export default TaskList;