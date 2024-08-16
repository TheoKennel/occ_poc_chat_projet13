import {Component, OnDestroy} from '@angular/core';
import {LocalStorageService} from "./storage/local-storage.service";
import {Observable, of, Subject} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements  OnDestroy {

  title = 'front';
  private ngUnsubscribe: any = new Subject();
  isMenuOpen = false;

  constructor(
    private localStorage: LocalStorageService,
  ) {
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }


  public $isLoggedIn(): Observable<boolean> {
    return of(this.localStorage.getItem('isLogged') === 'false');
  }
}

