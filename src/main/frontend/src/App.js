import React, {useEffect, useState, useCallback} from 'react';
import {useDropzone} from 'react-dropzone'
import logo from './logo.svg';
import './App.css';
import axios from 'axios'
// import pic from './images/attractive-beautiful-beauty-close-up-458718.jpg'
const UserProfiles = ()=>{

  const [userProfiles, setUserProfiles] = useState([])

  const fetchUserProfiles = ()=>{
    axios.get("http://localhost:8000/api/v1/user-profile").then(res =>{
      console.log(res)
      setUserProfiles(res.data)
    })
  }

  useEffect(()=>{
    fetchUserProfiles() 
  },[])

  return userProfiles.map((userProfiles, index)=>{
    console.log(userProfiles.userProfileId)
    return (
      
      <div key={index}>
       {userProfiles.userProfileId? <img src={axios.get(`http://localhost:8000/api/v1/user-profile/${userProfiles.userProfileId}/image/download`)}
       /> : null}
        
        <h1>{userProfiles.userName}</h1>
        <p>{userProfiles.userProfileId}</p>
        <Dropzone userProfileId={userProfiles.userProfileId}/>
        <br/>
      </div>
    )
  })
}

function Dropzone(props) {
  const onDrop = useCallback(acceptedFiles => {
   const file = acceptedFiles[0]
   console.log(file)
   const formData = new FormData()
   formData.append('file',file)
   axios.post(`http://localhost:8000/api/v1/user-profile/${props.userProfileId}/image/upload`,
   formData,
   {
     headers:{
        "Content-Type": "multipart/form-data"
     }
   }
   ).then(res=> console.log('file uploaded')).catch(err=> console.log(err))
  }, [])

  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some files here, or click to select files</p>
      }
    </div>
  )
}
function App() {
  return (
    <div className="App">
      <UserProfiles/>
    </div>
  );
}

export default App;
