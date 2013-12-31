//
//  IdeasVC.h
//  P2A
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface IdeasVC : UIViewController <UISearchBarDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) NSArray *dataArray;
@property (strong, nonatomic) NSArray *filteredArray;

@end
