import { useState, useEffect } from 'react';
import axios from 'axios';

const useAxios = (url) =>{
    const [data,setData] =  useState(null);
    const [isPending,setIsPending] =  useState(true);
    const [error,setError] =  useState(null);
    
    useEffect(() => {
        axios.get(url).then(res => {
            //console.log(res);
            
            setData(res.data);
            setIsPending(false);
            setError(null);
        });
    },[url])

    return {data, isPending, error}
}


export default useAxios;