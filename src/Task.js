import { useNavigate } from "react-router-dom";


    
const Task = ({task}) => {
    const navigate = useNavigate();
    console.log(task);
    
    const handleDelete= (id,platform) => {
        fetch('http://localhost:9191/delete/'+id,{
            method : 'DELETE',
        }).then(()=>{
            console.log('Blog deleted');
            navigate('/');
            
        })
    }
    return ( 
        <div className="task">
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
                                    <i onClick={() => handleDelete(task.id)} className={"fas " + ( task.status === 0 ? 'fa-check ' : 'fa-times text-muted')}></i>

                                </div>
                            </div>
                        </div>
                    </div>
               
                </div>
        </div>
     );
}
 
export default Task;