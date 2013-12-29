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
    
    
    
    
}

- (void) viewWillDisappear:(BOOL)animated {
    self.title=nil;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    if ([self respondsToSelector:@selector(edgesForExtendedLayout)])
        self.edgesForExtendedLayout = UIRectEdgeNone;

    UIBarButtonItem *done=[[UIBarButtonItem alloc] initWithTitle:@"Done" style:UIBarButtonItemStylePlain target:self action:@selector(done)];
    self.navigationItem.rightBarButtonItem=done;

    
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) done {
    
    
    NSMutableURLRequest *request = [NSMutableURLRequest
                                    requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@/api/project",host]]];
    
    NSDictionary *requestData = [[NSDictionary alloc] initWithObjectsAndKeys:
                                 @"New project", @"name",
                                 @"New description", @"description",
                                 nil];
    NSError *error;
    NSData *postData = [NSJSONSerialization dataWithJSONObject:requestData options:0 error:&error];
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPMethod:@"POST"];
    [request setHTTPBody:postData];
//    NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    
    
//    
//    
//    AFHTTPClient *httpClient = [[AFHTTPClient alloc] initWithBaseURL:[NSURL URLWithString:host]];
    
//    NSURLRequest *req = [httpClient multipartFormRequestWithMethod:@"POST" path:@"api/project" parameters:nil constructingBodyWithBlock: ^(id <AFMultipartFormData>formData) {
    
        //        [formData appendPartWithFormData:[filename dataUsingEncoding:NSUTF8StringEncoding] name:@"filename"];
        //        [formData appendPartWithFormData:[filepath  dataUsingEncoding:NSUTF8StringEncoding] name:@"fullpath"];
        //        [formData appendPartWithFormData:[token  dataUsingEncoding:NSUTF8StringEncoding] name:@"token"];
        //        [formData appendPartWithFormData:[hash  dataUsingEncoding:NSUTF8StringEncoding] name:@"hash"];
        //        [formData appendPartWithFormData:[creationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"whencreated"];
        //        [formData appendPartWithFormData:[modificationDate  dataUsingEncoding:NSUTF8StringEncoding] name:@"lastmodified"];
//    }];
    
    
    NSLog(@"Request: %@",[[request URL] absoluteString]);
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc]initWithRequest:request];
    [operation  setCompletionBlockWithSuccess: ^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog (@"JSON: %@",[operation responseString]);
        
        [self.navigationController popViewControllerAnimated:YES];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"Failure: %@",[error description]);
        operation=nil;
    }];
    
    [operation start];

}

@end
