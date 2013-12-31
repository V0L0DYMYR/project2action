//
//  IdeasVC.m
//  P2A
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import "IdeasVC.h"
//#import <RKObjectMapping.h>

@interface IdeasVC ()

@end

@implementation IdeasVC

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
    self.title=@"ІДЕЇ";
    
    NSLog(@"Load ideas");
    
    
    RKObjectMapping *mapping = [RKObjectMapping mappingForClass:[Idea class]];
    [mapping addAttributeMappingsFromDictionary:@{
                                                  @"id_":@"id",
                                                  @"name":@"name",
                                                  @"description":@"description",
                                                  @"authorId":@"authorId"
                                                  }];
    
    RKResponseDescriptor *responseDescriptor = [RKResponseDescriptor responseDescriptorWithMapping:mapping method:RKRequestMethodAny pathPattern:nil keyPath:nil statusCodes:nil];
    NSURL *url = [NSURL URLWithString:[NSString stringWithFormat:@"%@/api/idea",host]];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    RKObjectRequestOperation *operation = [[RKObjectRequestOperation alloc] initWithRequest:request responseDescriptors:@[responseDescriptor]];
    [operation setCompletionBlockWithSuccess:^(RKObjectRequestOperation *operation, RKMappingResult *result) {
        NSLog(@"Array: %@", [result array]);
        NSLog(@"COUNT: %d",[[result array] count]);

        self.dataArray=[result array];
        self.filteredArray=self.dataArray;
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
    return [self.filteredArray count];
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
    
    Idea *i=[self.filteredArray objectAtIndex:indexPath.row];
    
    [cell setAccessoryType:UITableViewCellAccessoryDisclosureIndicator];
    [cell.textLabel setText:i.name];
    [cell.detailTextLabel setText:i.description];
    [cell.textLabel setFont:[UIFont boldSystemFontOfSize:14]];
    [cell.detailTextLabel setTextColor:[UIColor whiteColor]];
    [cell.textLabel setTextColor:[UIColor whiteColor]];
    [cell.imageView setImage:[UIImage imageNamed:@"ava"]];
    [cell setBackgroundColor:[UIColor clearColor]];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}


#pragma mark - Search
- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar {
    searchBar.text=@"";
    self.filteredArray=self.dataArray;
    [searchBar resignFirstResponder];
}

- (void)searchBar:(UISearchBar *)searchBar textDidChange:(NSString *)searchText {
    
    NSPredicate *p =[NSPredicate predicateWithFormat:@"name CONTAINS[c] %@",searchText ];

//    NSPredicate *p = [NSPredicate predicateWithFormat:@"name LIKE %@",searchText];
    self.filteredArray = [self.dataArray filteredArrayUsingPredicate:p];
    
    [self.tableView reloadData];
}
@end
