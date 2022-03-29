import { useState,useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const AdminUserPage = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("adminEmail") === null) {
            navigate('/register');
        }
    })

    const [users,setUsers] = useState(null);
    useEffect(() => {
        fetch('//13.127.82.222:8778/Users/')
            .then((res)=>{
                return res.json()
            })
            .then((data)=>{
                //Sorting Tasks

                var byTasks = data.slice(0);
                byTasks.sort(function(a,b) {
                    return b.tasksCompleted - a.tasksCompleted;
                });
                setUsers(byTasks);
            })
    },[])

    const handleDelete = (id) => {
        fetch('//13.127.82.222:8778/deleteUser/'+id,{
            method : 'DELETE'
        }).then((res)=>{
            return res.json()
        })
        .then((data)=>{
            const newusers = users.filter(user => user.id!==id);
            setUsers(newusers);
        })
    }

    return ( 
        <div className="userTable">
            <div className="card">
                <div className="card-header">
                    <p className="lead"><i className="fa fa-list"></i>&nbsp;&nbsp;All Users</p>
                </div>
                <div className="card-body">
                <table className="table">
                <thead className="thead-dark">
                    <tr>
                    <th scope="col">User Mail</th>
                    <th scope="col">Name</th>
                    <th scope="col">Task Completed</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                {  users && <tbody>
                    { users.map((user, index)=>(
                            <tr key={user.id} className={(index===0 ? 'table-danger' : (index===1 ? 'table-success' : (index===2 ? 'table-primary' : '')) )}>
                                <td>{user.email}</td>
                                <td>{user.name}</td>
                                <td>{user.tasksCompleted}</td>
                                <td>{user.phoneNo}</td>
                                <td onClick={() => handleDelete(user.id)} className='text-danger del-task'><i className="fa fa-trash"></i>&nbsp;&nbsp;Delete</td>
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
 
export default AdminUserPage;