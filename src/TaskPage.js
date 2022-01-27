import TaskList from './Tasklist';

const TaskPage = ({tasks,platform}) =>{

    return(
        <div className="Tasks-tasks">
            <h1 className="display-4 head-name"> {platform} Tasks</h1>
            <div className="row justify-content-center ">
                <div className="d-flex col-md-8 align-items-center">
                    <TaskList tasks={tasks.filter((task)=> task.platform === platform)}></TaskList>
                </div>
                
            </div>
        </div>
    );
}
export default TaskPage;