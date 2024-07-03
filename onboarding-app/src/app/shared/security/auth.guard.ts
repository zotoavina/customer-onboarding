import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ManagerServiceService } from 'src/app/services/manager-service.service';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate {

  constructor(
    private managerSrv: ManagerServiceService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    let url: string = state.url;
    return this.checkUserLogin(route,url);
  }


  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.managerSrv.isAuthenticatedUser()) {
      const userRole = this.managerSrv.getRole();
      if (route.data['role'] && (route.data['role']).indexOf(userRole) === -1) {
        this.router.navigate(['/login']);
        return false;
      }
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }

}
