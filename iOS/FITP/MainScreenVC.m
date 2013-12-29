//
//  MainScreenVC.m
//  FITP
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import "MainScreenVC.h"

@interface MainScreenVC ()

@end

@implementation MainScreenVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void) viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    self.title=@"Проекти в дію!";
}
- (void) viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    self.title=nil;
}


- (void)viewDidLoad {

    [super viewDidLoad];
    UIBarButtonItem *logout=[[UIBarButtonItem alloc] initWithTitle:@"Logout" style:UIBarButtonItemStylePlain target:self action:@selector(logoutActn:)];
    self.navigationItem.leftBarButtonItem=logout;
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)logoutActn:(id)sender {
    NSHTTPCookieStorage *cookieJar = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    [cookieJar deleteCookie:[myAppDelegate theCookie]];
    
    NSLog(@"LOGOUT");
    
    AFHTTPClient *httpClient = [[AFHTTPClient alloc] initWithBaseURL:[NSURL URLWithString:host]];
    
    NSURLRequest *req = [httpClient multipartFormRequestWithMethod:@"POST" path:@"oauth2/google/logout" parameters:nil constructingBodyWithBlock: ^(id <AFMultipartFormData>formData) {
        
//        [formData appendPartWithFormData:[filename dataUsingEncoding:NSUTF8StringEncoding] name:@"filename"];
//        [formData appendPartWithFormData:[filepath  dataUsingEncoding:NSUTF8StringEncoding] name:@"fullpath"];
//        [formData appendPartWithFormData:[token  dataUsingEncoding:NSUTF8StringEncoding] name:@"token"];
//        [formData appendPartWithFormData:[hash  dataUsingEncoding:NSUTF8StringEncoding] name:@"hash"];
//        [formData appendPartWithFormData:[creationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"whencreated"];
//        [formData appendPartWithFormData:[modificationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"lastmodified"];
    }];
    
    
    
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc]initWithRequest:req];
    [operation  setCompletionBlockWithSuccess: ^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog (@"JSON: %@",[operation responseString]);
        
        [self.navigationController popViewControllerAnimated:YES];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        operation=nil;
    }];
    
    [operation start];
    
}
@end
