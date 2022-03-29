import { useState,useEffect } from 'react';
import { Link,useNavigate } from 'react-router-dom';

const AdminUserPage = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("adminEmail") === null) {
            navigate('/login');
        }
    })

    const [users,setUsers] = useState(null);
    useEffect(() => {
        fetch('//localhost:8778/Users/')
            .then((res)=>{
                return res.json()
            })
            .then((data)=>{
                setUsers(data);
            })
    },[])

    const handleDelete = (id) => {
        fetch('//localhost:8778/deleteUser/'+id,{
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
                    <p className="lead"><i className="fa fa-list"></i>&nbsp;&nbsp; All Users</p>
                </div>
                <div className="card-body">
                <table className="table">
                <thead className="thead-dark">
                    <tr>
                    <th scope="col">Id</th>
                    <th scope="col">User Mail</th>
                    <th scope="col">Name</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                {  users && <tbody>
                    { users.map((user)=>(
                            <tr key={user.id} className="table-success">
                                <td><small>{user.id}</small></td>
                                <td>{user.email}</td>
                                <td>{user.name}</td>
                                <td>{user.phoneNo}</td>
                                <td onClick={() => handleDelete(user.id)} className='text-danger del-user'><i className="fa fa-trash"></i>&nbsp;&nbsp;Delete</td>
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