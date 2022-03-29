import { useState,useEffect } from 'react';
import { Link,useNavigate } from 'react-router-dom';

const AdminTaskPage = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("adminEmail") === null) {
            navigate('/login');
        }
    })

    const [tasks,setTasks] = useState(null);
    useEffect(() => {
        fetch('//localhost:8778/Tasks/')
            .then((res)=>{
                return res.json()
            })
            .then((data)=>{
                setTasks(data);
            })
    },[])

    const handleDelete = (id) => {
        fetch('//localhost:8778/deleteTask/'+id,{
            method : 'DELETE'
        }).then((res)=>{
            return res.json()
        })
        .then((data)=>{
            const newTasks = tasks.filter(task => task.id!==id);
            setTasks(newTasks);
        })
    }

    return ( 
        <div className="taskTable">
            <div className="card">
                <div className="card-header">
                    <p className="lead"><i className="fa fa-list"></i>&nbsp;&nbsp; All Tasks</p>
                </div>
                <div className="card-body">
                <table className="table">
                <thead className="thead-dark">
                    <tr>
                    <th scope="col">Time</th>
                    <th scope="col">User Mail</th>
                    <th scope="col">Project</th>
                    <th scope="col">Task</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                {  tasks && <tbody>
                    { tasks.map((task)=>(
                            <tr key={task.id} className={( task.status === 1 ? 'table-success' : 'table-primary')}>
                                <td><small>{task.time.slice(0,10)} {task.time.slice(11,19)}</small></td>
                                <td>{task.receiver}</td>
                                <td>{task.projectName}</td>
                                <td>{task.taskName}</td>
                                <td onClick={() => handleDelete(task.id)} className='text-danger del-task'><i className="fa fa-trash"></i>&nbsp;&nbsp;Delete</td>
                            </tr>
                    ))
                    }
                </tbody> }
            </table> 
                </div>
            </div>
       </div>
     );
}
 
export default AdminTaskPage;