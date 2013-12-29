//
//  ProjectsVC.m
//  FITP
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import "ProjectsVC.h"
#import "ProjectVC.h"
@interface ProjectsVC ()

@end

@implementation ProjectsVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void) viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    self.title=@"ПРОЕКТИ";
    
    RKObjectMapping *mapping = [RKObjectMapping mappingForClass:[Project class]];
    [mapping addAttributeMappingsFromDictionary:@{
                                                  @"id_":@"id",
                                                  @"name":@"name",
                                                  @"description":@"description",
                                                  @"authorId":@"authorId"
                                                  }];
    
    RKResponseDescriptor *responseDescriptor = [RKResponseDescriptor responseDescriptorWithMapping:mapping method:RKRequestMethodAny pathPattern:nil keyPath:nil statusCodes:nil];
    NSURL *url = [NSURL URLWithString:[NSString stringWithFormat:@"%@/api/project",host]];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    RKObjectRequestOperation *operation = [[RKObjectRequestOperation alloc] initWithRequest:request responseDescriptors:@[responseDescriptor]];
    [operation setCompletionBlockWithSuccess:^(RKObjectRequestOperation *operation, RKMappingResult *result) {
        NSLog(@"Array: %@", [result array]);
        NSLog(@"COUNT: %d",[[result array] count]);
        self.dataArray=[result array];
        [self.tableView reloadData];
    } failure:nil];
    [operation start];
    
    
    
}

- (void) viewWillDisappear:(BOOL)animated {
    self.title=nil;
}


- (void)viewDidLoad
{
    [super viewDidLoad];
    if ([self respondsToSelector:@selector(edgesForExtendedLayout)])
        self.edgesForExtendedLayout = UIRectEdgeNone;
    [self.tableView setBackgroundColor:[UIColor clearColor]];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.dataArray count];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {

    return 40;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier;
    CellIdentifier= @"TableCell1";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        cell = [[UITableViewCell alloc]
                initWithStyle:UITableViewCellStyleSubtitle
                reuseIdentifier:CellIdentifier];
    }
    
    Project *p=[self.dataArray objectAtIndex:indexPath.row];
    
    [cell setAccessoryType:UITableViewCellAccessoryDisclosureIndicator];
    [cell.textLabel setText:p.name];
    [cell.detailTextLabel setText:p.description];
    [cell.textLabel setFont:[UIFont boldSystemFontOfSize:14]];
    [cell.detailTextLabel setTextColor:[UIColor whiteColor]];
    [cell.textLabel setTextColor:[UIColor whiteColor]];
    [cell.imageView setImage:[UIImage imageNamed:@"ava"]];
    [cell setBackgroundColor:[UIColor clearColor]];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    ProjectVC *pvc=[[UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil] instantiateViewControllerWithIdentifier:@"ProjectVC"];
    pvc.title=[[[self.tableView cellForRowAtIndexPath:indexPath] textLabel] text];
    [self.navigationController pushViewController:pvc animated:YES];
}

@end
