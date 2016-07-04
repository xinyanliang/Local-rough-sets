function [lowApproxmationResult] = MatGMGRS(data, multiRelation_cell, targetX, alpha)
% data: cell(objLen,attrLen)

% multiRelation_cell: cell(matrix(a1, a2), cell(a3, a4, a5), ... ,cell()) its lenth is
% length(multiRelation_cell)

%targetX: matrix(1,2,3,4,length(targetX))

% constructing the data according to multiRelation_cell
m = length(multiRelation_cell);
multiData = cell(m,1);
for i=1 : m
    multiData{i}=data(:,multiRelation_cell{i});
end
%

targetX_length = length(targetX);
data_length = size(data,1);

multistructures = cell(m);
lowApproxmation=[];

tic;

for targetX_i=1 :data_length
    for granular_i=1 : m
        granular_sum=0;
        attr_length = length(multiRelation_cell{granular_i});
        for data_i=1: data_length
            attrequalcount = 0;
            for attr_i=1 : attr_length
                if multiData{granular_i}(targetX_i ,attr_i)==multiData{granular_i}(data_i, attr_i)
                    attrequalcount=attrequalcount+1;
                else
                    break;
                end
            end
            if attrequalcount == attr_length
                multistructures{granular_i}=[multistructures{granular_i} data_i ];
            end
        end
        if length(intersect(multistructures{ granular_i},targetX))/length(multistructures{granular_i})>= alpha;
            granular_sum=granular_sum+1;
        end
    end
    
    if granular_sum>0
        lowApproxmation=[lowApproxmation targetX_i];
    end
end
toc;
lowApproxmationResult = lowApproxmation;

end

