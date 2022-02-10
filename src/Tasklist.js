import Task from "./Task";

const TaskList = ({tasks}) => {
    return ( 
        <div className="task-list ">
            { tasks.map((task)=>(
                <Task task={task} key={task.id}></Task>
            ))}
        </div>
     );
}
 
export default TaskList;