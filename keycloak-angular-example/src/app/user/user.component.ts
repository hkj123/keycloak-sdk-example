import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import * as Keycloak from 'keycloak-js';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Input() user;
  @Output() login(){
    // var keycloak = Keycloak('http://localhost:8080/myapp/keycloak.json');
    var keycloak = Keycloak({
      url: 'http://192.168.11.82:8180/auth',
      realm: 'openbanking',
      clientId: 'resource-proxy',
      // clientSecret:'3ce39f6f-9b84-4e7d-a9b8-292afc2f1911'
      redirectUri:'http://192.168.11.82:8180/auth/admin/openbanking/console'
    });
    //init { flow: 'implicit' }  { onLoad: 'login-required' } { flow: 'hybrid' } { adapter: 'cordova-native' }
    keycloak.init({ onLoad: 'login-required' }).success(function(authenticated) {
      alert(authenticated ? 'authenticated' : 'not authenticated');
    }).error(function() {
      alert('failed to initialize');
    });
  };
  // @Output() login(){
  //   console.log("d登录前夕")
  //   var keycloak = Keycloak({
  //     url: 'http://192.168.11.82:8180/auth',
  //     realm: 'openbanking',
  //     clientId: 'resource-proxy',
  //     clientSecret:'3ce39f6f-9b84-4e7d-a9b8-292afc2f1911',
  //     // redirectUri:'http://192.168.11.82:8180/auth/admin/openbanking/console'
  //   });
  //   console.log("实例化keycloak",keycloak);
  //   //init { flow: 'implicit' }  { onLoad: 'login-required' } { flow: 'hybrid' } { adapter: 'cordova-native' }
  //   keycloak.login({ scope: 'openbanking',prompt:'login',action:'register'}).success(function(responseType) {
  //     console.log("登录")
  //     // alert(responseType);
  //   }).error(function() {
  //     alert('failed to initialize');
  //   });
  // };
  constructor() { }
  ngOnInit() {
    console.log("调用用户管理")
  }
}
