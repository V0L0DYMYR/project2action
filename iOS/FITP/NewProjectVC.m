//
//  NewProjectVC.m
//  FITP
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import "NewProjectVC.h"

@interface NewProjectVC ()

@end

@implementation NewProjectVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void) viewWillAppear:(BOOL)animated {
self.title=@"Новий Проект";
    
    
    AFHTTPClient *httpClient = [[AFHTTPClient alloc] initWithBaseURL:[NSURL URLWithString:host]];
    
    NSURLRequest *req = [httpClient multipartFormRequestWithMethod:@"POST" path:@"api/project" parameters:nil constructingBodyWithBlock: ^(id <AFMultipartFormData>formData) {
        
        //        [formData appendPartWithFormData:[filename dataUsingEncoding:NSUTF8StringEncoding] name:@"filename"];
        //        [formData appendPartWithFormData:[filepath  dataUsingEncoding:NSUTF8StringEncoding] name:@"fullpath"];
        //        [formData appendPartWithFormData:[token  dataUsingEncoding:NSUTF8StringEncoding] name:@"token"];
        //        [formData appendPartWithFormData:[hash  dataUsingEncoding:NSUTF8StringEncoding] name:@"hash"];
        //        [formData appendPartWithFormData:[creationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"whencreated"];
        //        [formData appendPartWithFormData:[modificationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"lastmodified"];
    }];
    
    
    NSLog(@"Req: %@",[[req URL] absoluteString]);
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc]initWithRequest:req];
    [operation  setCompletionBlockWithSuccess: ^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog (@"JSON: %@",[operation responseString]);
        
        [self.navigationController popViewControllerAnimated:YES];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"Failure: %@",[error description]);
        operation=nil;
    }];
    
    [operation start];
    
    
}

- (void) viewWillDisappear:(BOOL)animated {
    self.title=nil;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
