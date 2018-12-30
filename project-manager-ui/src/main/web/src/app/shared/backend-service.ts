import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouterModule, Router } from '@angular/router';
import { Config } from '../env/index';

@Injectable()
export class BackendService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  getTasks(projectId) {
    return this.httpClient.get(Config.API + '/pm/getTask/' + projectId);
  }

  getParentTasks(projectId) {
    return this.httpClient.get(Config.API + '/pm/getParentTask/' + projectId);
  }

  getUsers(inputParam) {
    return this.httpClient.get(Config.API + '/pm/getUser', inputParam);
  }

  updateUser(inputParam) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(Config.API + '/pm/updateUser',
      inputParam,
      { headers: headers });
  }

  deleteUser(userId) {
    return this.httpClient.delete(Config.API + '/pm/deleteUser/' + userId);
  }

  getProjects(inputParam) {
    return this.httpClient.get(Config.API + '/pm/getProject', inputParam);

  }

  deleteProject(projectId) {
    return this.httpClient.delete(Config.API + '/pm/deleteProject/' + projectId);
  }

  updateProject(inputParam) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(Config.API + '/pm/updateProject',
      inputParam,
      { headers: headers });
  }

  updateTask(inputParam) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(Config.API + '/pm/updateTask',
      inputParam,
      { headers: headers });
  }

}
