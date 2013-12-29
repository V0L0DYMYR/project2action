//
//  ViewController.m
//  FITP
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void) viewWillDisappear:(BOOL)animated {
    self.title=@"Вийти";
}
- (void) viewWillAppear:(BOOL)animated {
    self.title=@"Проекти в Дію!";
}

- (void) viewDidAppear:(BOOL)animated {
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@/project2action/signin.html",host]]]];

}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSString*)getTokenFromCookie {
    NSHTTPCookie *cookie;
    NSHTTPCookieStorage *cookieJar = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    for (cookie in [cookieJar cookies]) {
//        NSLog(@"%@ = %@",[cookie name],[cookie value]);
//        if ([[cookie domain] isEqualToString:self.domain]) {
            if ([[cookie name] isEqualToString:@"TOKEN"]) {
                [myAppDelegate setTheCookie:cookie];
                return [[myAppDelegate theCookie] value];
            }
        
//        }
    }
    return nil;
}

- (void)webViewDidFinishLoad:(UIWebView *)theWebView
{
    NSString* token = [self getTokenFromCookie];
    if (token) {
        MainScreenVC *vc=[self.storyboard instantiateViewControllerWithIdentifier:@"MainScreenVC"];
        [self.navigationController pushViewController:vc animated:YES];
    }
}

@end
