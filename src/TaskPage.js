import { useState,useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import TaskList from './Tasklist';

const TaskPage = ({platform}) =>{
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("wcEmail") === null) {
            navigate('/login');
        }
    })
    const email = sessionStorage.getItem("wcEmail");
    const [tasks,setTasks] =  useState(null);
    useEffect(() => {
        fetch('//13.127.82.222:9191/TaskByEmail/'+ email)
            .then((res)=>{
                return res.json()
            })
            .then((data)=>{
                setTasks(data);
                console.log(data)
            })
    },[email])


    const handleUpdate = (id) => {
        fetch('//13.127.82.222:9191/updateStatus/'+id)
        .then((res)=>{
            return res.json()
        })
        .then((data)=>{
            if (data.status==="1") {
                
                if (data.action==="delete") {
                    alert("Task Deleted")
                    const newTasks = tasks.filter(task => task.id!==id);
                    setTasks(newTasks);
                }
                else if(data.action==="update"){
                    alert("Task Marked as Updated")
                    const newTasks = tasks.map(function(task){
                        if (task.id===id) {
                            task.status =1;
                        }
                        return task;
                        
                    });
                    setTasks(newTasks);
                }
            }
        })
    }

    return(
        <div className="Tasks-tasks">
            <h1 className="display-4 head-name"> {platform} Tasks</h1>
            <hr />
            <div className="row justify-content-center ">
                <div className="d-flex col-md-8 align-items-center">
                   {tasks && <TaskList handleUpdate={handleUpdate} tasks={tasks.filter((task)=> task.platform === platform)}></TaskList>} 
                </div>
                
            </div>
        </div>
    );
}
export default TaskPage;