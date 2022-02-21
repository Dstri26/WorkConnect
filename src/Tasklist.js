const TaskList = ({tasks,handleUpdate}) => {
    return ( 
        <div className="task-list ">
            { tasks.map((task)=>(
                <div className="task" key={task.id}>
                <div className={"alert text-left " + ( task.status === 0 ? 'alert-warning ' : 'alert-secondary text-muted')} role="alert">
                        <div className="row">
                            <div className="d-flex col-md-11 align-items-center">
                                <div>
                                    <small><strong>{task.sender}</strong> | {Date(task.time).slice(3, 25)} :</small>
                                    <p className="taskTxt"><em>{task.taskName}</em></p>
                                </div>
                                
                            </div>
                            <div className="col-md-1 text-center d-flex align-items-center justify-content-center symbols">
                                <div className="row">
                                    <div className="col-12">
                                        <i onClick={() => handleUpdate(task.id)} className={"fas " + ( task.status === 0 ? 'fa-check text-success' : 'fa-trash text-danger')}></i>
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