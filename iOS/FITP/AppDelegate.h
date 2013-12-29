//
//  AppDelegate.h
//  FITP
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ViewController.h"
#import "MainScreenVC.h"
#import "AFHTTPClient.h"
#import "AFHTTPRequestOperation.h"

#define myAppDelegate (AppDelegate*)[[UIApplication sharedApplication] delegate]

#define host @"http://ec2-54-202-158-184.us-west-2.compute.amazonaws.com:9000"
//#define host @"http://127.0.0.1:8000"

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) ViewController *loginVC;
@property (strong, nonatomic) MainScreenVC *mainScreenVC;
@property (strong, nonatomic)   NSHTTPCookie *theCookie;

@end
