import axios from 'axios'

//react can't use http service, so we included axios library, imported
const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/employees";
const EMPLOYEE_ID = 16;

class EmployeeService{

    getEmployee(){
        //axios has many methods, like put, post, get 
        return axios.get(EMPLOYEE_SERVICE_BASE_URL + '/' + EMPLOYEE_ID);
    }
}


//this makes the class as component so other can use it
export default new EmployeeService