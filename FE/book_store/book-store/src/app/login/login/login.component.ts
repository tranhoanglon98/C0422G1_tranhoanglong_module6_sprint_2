import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShareDataService} from '../../service/share-data.service';
import {SecurityService} from '../../service/security.service';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginResponse} from '../../model/login-response';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';
import {FacebookLoginProvider, GoogleLoginProvider, SocialAuthService} from 'angularx-social-login';
import {CartService} from '../../service/cart.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {

  loginForm: FormGroup;
  loginResponse: LoginResponse;
  rememberMe = false;
  previousUrl: string;

  constructor(private shareDataService: ShareDataService,
              private securityService: SecurityService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private authService: SocialAuthService,
              private cartService: CartService,
              private toastrService: ToastrService) {
    shareDataService.changeLoginModuleStatus(true)
    shareDataService.currentPreviousUrl.subscribe(url => this.previousUrl = url)
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(""),
      password: new FormControl("")
    })
  }

  submitLogin() {
    this.securityService.login(this.loginForm.value).subscribe(loginResponse => {
      this.loginResponse = loginResponse;
      if (this.rememberMe){
        this.tokenStorageService.localStorageSave(loginResponse);
      }else {
        this.tokenStorageService.sessionStorageSave(loginResponse);
      }
      this.shareDataService.changeLoginStatus(true)
      this.getCartItems()
      this.router.navigateByUrl(this.previousUrl);
      this.toastrService.success('Chào ' + loginResponse.name,'Đăng nhập thành công',{
        positionClass: 'toast-top-center'
      })
    }, error => {
      this.toastrService.error('Tài khoản hoặc mật khẩu không đúng !!!', 'Đăng nhập thất bại',{
        positionClass: 'toast-top-center'
      })
    } )
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(data => {
      console.log(data);
      this.securityService.loginBySocial(data.idToken,'google').subscribe(loginResponse => {
        this.tokenStorageService.sessionStorageSave(loginResponse);
        this.shareDataService.changeLoginStatus(true)
        this.getCartItems()
        this.router.navigateByUrl(this.previousUrl);
        this.toastrService.success('Chào ' + loginResponse.name,'Đăng nhập thành công',{
          positionClass: 'toast-top-center'
        })
      })
    });
  }
  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
      this.securityService.loginBySocial(data.authToken,'facebook').subscribe(loginResponse => {
        this.tokenStorageService.sessionStorageSave(loginResponse);
        this.shareDataService.changeLoginStatus(true)
        this.getCartItems()
        this.router.navigateByUrl(this.previousUrl);
        this.toastrService.success('Chào ' + loginResponse.name,'Đăng nhập thành công',{
          positionClass: 'toast-center-center'
        })
      })
    });
  }

  getCartItems(){
    this.cartService.getCartItems().subscribe(amount => {
        this.shareDataService.changeCartItemsAmount(amount.length)
    })
  }

  ngOnDestroy(): void {
    this.shareDataService.changeLoginModuleStatus(false);
  }
}
