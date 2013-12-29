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

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) ViewController *loginVC;
@property (strong, nonatomic) MainScreenVC *mainScreenVC;

@end
