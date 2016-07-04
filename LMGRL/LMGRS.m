function [lowApproxmationResult] = LMGRS(data, multiRelation_cell, targetX, alpha)
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

multistructures = cell(targetX_length,m);
tic;

for granular_i=1 : m %control maltiGranular relations
    attr_length = length(multiRelation_cell{granular_i});
    for targetX_i=1 :targetX_length
        for data_i=1: data_length
            attrequalcount = 0;
            for attr_i=1 : attr_length
                if strcmp(multiData{granular_i}{targetX_i ,attr_i},multiData{granular_i}{data_i, attr_i})
                    attrequalcount=attrequalcount+1;
                else
                    break;
                end
            end
            if attrequalcount == attr_length
                multistructures{targetX_i, granular_i}=[multistructures{targetX_i, granular_i} data_i ];
            end
        end
        
    end
end

% refusing multigranular struction
lowApproxmation=[];
for targetX_i=1 :targetX_length
    granular_sum=0;
    for granular_i=1 : m %control maltiGranular relations
        if length(intersect(multistructures{targetX_i, granular_i},targetX))/length(multistructures{targetX_i, granular_i})>= alpha;
            granular_sum=granular_sum+1;
        end
    end
    if granular_sum>0
        lowApproxmation=[lowApproxmation targetX_i];
    end
end
lowApproxmationResult = lowApproxmation;
toc;

end

