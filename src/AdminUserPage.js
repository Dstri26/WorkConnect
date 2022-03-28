import { useState,useEffect } from 'react';
import { Link,useNavigate } from 'react-router-dom';

const AdminUserPage = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if (sessionStorage.getItem("adminEmail") === null) {
            navigate('/login');
        }
    })

    const [users,setUsers] =  useState(null);
    useEffect(() => {
        fetch('//localhost:8778/TaskByEmail/')
            .then((res)=>{
                return res.json()
            })
            .then((data)=>{
                setUsers(data);
                console.log(data)
            })
    },[])

    return ( 
        <div className="userTable">
           <table className="table">
            <thead className="thead-primary">
                <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                </tr>
                <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                </tr>
                <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
                </tr>
            </tbody>
            </table>
       </div>
     );
}
 
export default AdminUserPage;