import { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import TaskList from './Tasklist';
import useFetch from './useFetch';

const TaskPage = ({platform}) =>{
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("wcEmail") === null) {
            navigate('/login');
        }
    })
    const email = sessionStorage.getItem("wcEmail");
    const { data:tasks, isPending, error} = useFetch('http://localhost:9191/TaskByEmail/'+ email);
    return(
        <div className="Tasks-tasks">
            <h1 className="display-4 head-name"> {platform} Tasks</h1>
            <div className="row justify-content-center ">
                <div className="d-flex col-md-8 align-items-center">
                   {tasks && <TaskList tasks={tasks.filter((task)=> task.platform === platform)}></TaskList>} 
                </div>
                
            </div>
        </div>
    );
}
export default TaskPage;